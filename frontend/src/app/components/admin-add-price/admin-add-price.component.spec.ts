import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminAddPricesComponent } from './admin-add-price.component';

describe('AdminAddPricesComponent', () => {
  let component: AdminAddPricesComponent;
  let fixture: ComponentFixture<AdminAddPricesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [AdminAddPricesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminAddPricesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
