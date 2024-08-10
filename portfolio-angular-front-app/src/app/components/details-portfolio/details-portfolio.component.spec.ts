import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailsPortfolioComponent } from './details-portfolio.component';

describe('DetailsPortfolioComponent', () => {
  let component: DetailsPortfolioComponent;
  let fixture: ComponentFixture<DetailsPortfolioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DetailsPortfolioComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DetailsPortfolioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
