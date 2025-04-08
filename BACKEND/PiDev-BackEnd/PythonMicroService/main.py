from fastapi import FastAPI, File, UploadFile, HTTPException
from fastapi.responses import JSONResponse
import fitz  # PyMuPDF
import docx
import io
import os
import google.generativeai as genai
import langdetect
from dotenv import load_dotenv

load_dotenv()

# Init FastAPI app
app = FastAPI()
api_key = os.getenv("GOOGLE_API_KEY")

# Configure Gemini
genai.configure(api_key=api_key)
model = genai.GenerativeModel("gemini-2.0-flash")

def extract_text_from_pdf(file_bytes):
    text = ""
    with fitz.open(stream=file_bytes, filetype="pdf") as doc:
        for page in doc:
            text += page.get_text()
    return text

def extract_text_from_docx(file_bytes):
    doc = docx.Document(io.BytesIO(file_bytes))
    return "\n".join([para.text for para in doc.paragraphs])

def detect_language(text):
    try:
        return langdetect.detect(text)
    except Exception:
        return "en"

def summarize_text(text, lang):
    prompt = {
        "en": f"Summarize the following text in 4 phrases :\n\n{text}",
        "fr": f"Résume le texte suivant dans 4 phrases :\n\n{text}",
    }.get(lang, f"Summarize this text:\n\n{text}")  # Default to English if unknown

    response = model.generate_content(prompt)
    return response.text

@app.post("/extract-text/")
async def extract_text(file: UploadFile = File(...)):
    filename = file.filename.lower()
    file_bytes = await file.read()

    if filename.endswith(".pdf"):
        try:
            text = extract_text_from_pdf(file_bytes)
        except Exception as e:
            raise HTTPException(status_code=500, detail=f"Error reading PDF: {str(e)}")
    elif filename.endswith(".docx"):
        try:
            text = extract_text_from_docx(file_bytes)
        except Exception as e:
            raise HTTPException(status_code=500, detail=f"Error reading DOCX: {str(e)}")
    else:
        raise HTTPException(status_code=400, detail="Unsupported file type. Upload a .pdf or .docx file.")

    # Detect language
    lang = detect_language(text)

    # Summarize with Gemini
    try:
        summary = summarize_text(text, lang)
        clean_summary = summary.replace("\n", " ").strip()

    except Exception as e:
        raise HTTPException(status_code=500, detail=f"Gemini API error: {str(e)}")

    return JSONResponse(content={
        "summary": clean_summary
    })
