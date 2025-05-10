import { TestBed } from '@angular/core/testing';

import { EventTopicService } from './event-topic.service';

describe('EventTopicService', () => {
  let service: EventTopicService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EventTopicService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
