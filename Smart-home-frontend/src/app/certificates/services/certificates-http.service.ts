import { Inject, Injectable } from '@angular/core';
import { APP_SERVICE_CONFIG, AppConfig } from '../../app-config/app-config';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class CertificatesHttpService {
  CREATE_CSR = 'csr';

  constructor(
    @Inject(APP_SERVICE_CONFIG) private config: AppConfig,
    private http: HttpClient
  ) {}

  sendCreateCSRRequest(
    commonName: string,
    organization: string,
    organizationalUnit: string,
    city: string,
    state: string,
    country: string,
    keySize: number,
    algorithm: string
  ) {
    return this.http.post(this.config.apiEndpoint + this.CREATE_CSR, {
      commonName,
      organization,
      organizationalUnit,
      city,
      state,
      country,
      keySize,
      algorithm,
    });
  }
}
