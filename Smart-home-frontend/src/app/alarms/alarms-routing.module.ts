import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AlarmsComponent} from "./components/alarms/alarms.component";
import {AllAlarmsTableComponent} from "./components/alarms/all-alarms-table/all-alarms-table.component";
import {AlarmsResolver} from "./resolvers/alarms.resolver";

const routes: Routes = [
  {
    path: '',
    component: AlarmsComponent,
    children: [
      {
        path: 'all',
        component: AllAlarmsTableComponent,
        resolve: [AlarmsResolver]
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AlarmsRoutingModule { }
