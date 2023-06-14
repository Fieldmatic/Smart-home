import { Inject, Injectable } from '@angular/core';
import { APP_SERVICE_CONFIG, AppConfig } from '../../app-config/app-config';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Property } from '../../shared/model/property';
import { PageResponse } from '../../shared/model/page-response';

@Injectable({
  providedIn: 'root',
})
export class UserPropertiesHttpService {
  private GET_ACCESSIBLE_PROPERTIES = 'property/accessible';
  private GET_PROPERTY_MESSAGES = (id: string) => `message/${id}`;

  private SEARCH_PROPERTY_MESSAGES = (id: string) => `message/filter/${id}`;

  constructor(
    @Inject(APP_SERVICE_CONFIG) private config: AppConfig,
    private http: HttpClient
  ) {}

  getAccessibleProperties() {
    return this.http.get<Property[]>(
      this.config.apiEndpoint + this.GET_ACCESSIBLE_PROPERTIES
    );
  }

  getPropertyMessages(id: string) {
    return this.http.get<PageResponse<string>>(
      this.config.apiEndpoint + this.GET_PROPERTY_MESSAGES(id)
    );
  }

  searchPropertyMessages(
    id: string,
    filter: string | void,
    pageNumber: number | void,
    pageSize: number | void
  ) {
    let params = new HttpParams();
    if (filter) {
      params = params.append('filter', filter);
    }
    if (pageNumber || pageNumber === 0) {
      params = params.append('pageNumber', pageNumber);
    }
    if (pageSize) {
      params = params.append('pageSize', pageSize);
    }
    return this.http.get<PageResponse<string>>(
      this.config.apiEndpoint + this.SEARCH_PROPERTY_MESSAGES(id),
      {
        params,
      }
    );
  }
}
