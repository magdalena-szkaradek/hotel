import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeAllClientsComponent } from './employee-all-clients.component';

describe('EmployeeAllClientsComponent', () => {
  let component: EmployeeAllClientsComponent;
  let fixture: ComponentFixture<EmployeeAllClientsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EmployeeAllClientsComponent ]
    })
    .compileComponents();  
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EmployeeAllClientsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
