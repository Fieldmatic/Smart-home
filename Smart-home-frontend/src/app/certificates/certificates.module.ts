import { NgModule } from '@angular/core';
import { CertificatesComponent } from './components/certificates/certificates.component';
import { CertificatesRoutingModule } from './certificates-routing.module';
import { SharedModule } from '../shared/shared.module';
import { NewCsrFormComponent } from './components/certificates/new-csr-form/new-csr-form.component';

@NgModule({
  declarations: [CertificatesComponent, NewCsrFormComponent],
  imports: [CertificatesRoutingModule, SharedModule],
})
export class CertificatesModule {}
