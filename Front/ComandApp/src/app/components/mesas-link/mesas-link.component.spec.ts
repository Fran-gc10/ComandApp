import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MesasLinkComponent } from './mesas-link.component';

describe('MesasLinkComponent', () => {
  let component: MesasLinkComponent;
  let fixture: ComponentFixture<MesasLinkComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MesasLinkComponent]
    });
    fixture = TestBed.createComponent(MesasLinkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
