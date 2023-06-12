import { Inject, Injectable } from '@angular/core';
import { APP_SERVICE_CONFIG, AppConfig } from '../../app-config/app-config';
import { HttpClient } from '@angular/common/http';
import { Property } from '../../shared/model/property';

@Injectable({
  providedIn: 'root',
})
export class UserPropertiesHttpService {
  private GET_ACCESSIBLE_PROPERTIES = 'property/accessible';
  private GET_PROPERTY_MESSAGES = (id: string) => `message/${id}`;

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
    return this.http.get<string[]>(
      this.config.apiEndpoint + this.GET_PROPERTY_MESSAGES(id)
    );
  }
}
