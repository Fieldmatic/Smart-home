import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AlarmsRoutingModule } from './alarms-routing.module';
import { AlarmsComponent } from './components/alarms/alarms.component';
import { AllAlarmsTableComponent } from './components/alarms/all-alarms-table/all-alarms-table.component';
import {SharedModule} from "../shared/shared.module";
import {StoreModule} from "@ngrx/store";
import * as fromAlarms from "../alarms/store/alarms.reducer";
import {EffectsModule} from "@ngrx/effects";
import {AlarmsEffects} from './store/alarms.effects';


@NgModule({
  declarations: [
    AlarmsComponent,
    AllAlarmsTableComponent
  ],
  imports: [
    SharedModule,
    StoreModule.forFeature('alarms', fromAlarms.reducer),
    EffectsModule.forFeature([AlarmsEffects]),
    AlarmsRoutingModule
  ]
})
export class AlarmsModule { }
