import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PortfolioTestComponent } from './portfolio-test.component';

describe('PortfolioTestComponent', () => {
  let component: PortfolioTestComponent;
  let fixture: ComponentFixture<PortfolioTestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PortfolioTestComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PortfolioTestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
