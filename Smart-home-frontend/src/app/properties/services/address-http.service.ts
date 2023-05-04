import { Inject, Injectable } from '@angular/core';
import { APP_SERVICE_CONFIG, AppConfig } from '../../app-config/app-config';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class AddressHttpService {
  constructor(
    @Inject(APP_SERVICE_CONFIG) private config: AppConfig,
    private http: HttpClient
  ) {}

  getAddressSearchResults(value: string) {
    const params = new HttpParams()
      .append('text', value)
      .append('format', 'json')
      .append('apiKey', this.config.addressApiKey)
      .append('lang', 'en')
      .append('filter', 'countrycode:rs');
    const headers = new HttpHeaders().append('skip', 'true');
    return this.http.get<{ results: { formatted: string }[] }>(
      this.config.addressApiEndpoint,
      {
        params,
        headers,
      }
    );
  }
}
