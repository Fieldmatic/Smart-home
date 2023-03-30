import { NgModule } from '@angular/core';
import { CertificatesComponent } from './components/certificates/certificates.component';
import { CertificatesRoutingModule } from './certificates-routing.module';
import { SharedModule } from '../shared/shared.module';
import { NewCsrFormComponent } from './components/certificates/new-csr-form/new-csr-form.component';
import { NewCertificateFormComponent } from './components/certificates/new-certificate-form/new-certificate-form.component';

@NgModule({
  declarations: [CertificatesComponent, NewCsrFormComponent, NewCertificateFormComponent],
  imports: [CertificatesRoutingModule, SharedModule],
})
export class CertificatesModule {}
