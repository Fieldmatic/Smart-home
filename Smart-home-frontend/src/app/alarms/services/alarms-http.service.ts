import {Inject, Injectable} from '@angular/core';
import {APP_SERVICE_CONFIG, AppConfig} from "../../app-config/app-config";
import {HttpClient, HttpParams} from "@angular/common/http";
import {SortDirection} from "../../shared/model/sort-direction";
import {PageResponse} from "../../shared/model/page-response";
import {User} from "../../shared/model/user.model";
import {Alarm} from "../../shared/model/alarm.model";

@Injectable({
  providedIn: 'root'
})
export class AlarmsHttpService {
  GET_ALARMS = 'alarm';

  constructor(@Inject(APP_SERVICE_CONFIG) private config: AppConfig,
              private http: HttpClient) { }

  getAlarms(
    pageSize?: number,
    pageNumber?: number
  ) {
    let params = new HttpParams();
    if (pageSize) {
      params = params.append('pageSize', pageSize);
    }
    if (pageNumber) {
      params = params.append('pageNumber', pageNumber);
    }
    return this.http.get<PageResponse<Alarm>>(
      this.config.apiEndpoint + this.GET_ALARMS,
      {
        params,
      }
    );
  }

}
