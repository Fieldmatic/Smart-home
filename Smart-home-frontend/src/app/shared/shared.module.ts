import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from '../material/material.module';
import { ReactiveFormsModule } from '@angular/forms';
import { FormNavigationComponent } from './form-navigation/form-navigation.component';

@NgModule({
  imports: [CommonModule, ReactiveFormsModule],
  exports: [
    CommonModule,
    MaterialModule,
    ReactiveFormsModule,
    FormNavigationComponent,
  ],
  declarations: [FormNavigationComponent],
})
export class SharedModule {}
