import { NgModule } from '@angular/core';
import { CertificatesAdminRoutingModule } from './certificates-admin-routing.module';
import { SharedModule } from '../shared/shared.module';
import { CertificatesAdminComponent } from './components/certificates-admin/certificates-admin.component';
import { CsrPendingTableComponent } from './components/certificates-admin/csr-pending-table/csr-pending-table.component';
import { StoreModule } from '@ngrx/store';
import * as fromCertificatesAdmin from './store/certificates-admin.reducer';
import { NewCertificateFormComponent } from './components/certificates-admin/new-certificate-form/new-certificate-form.component';
import { AllCertificatesTableComponent } from './components/certificates-admin/all-certificates-table/all-certificates-table.component';
import { EffectsModule } from '@ngrx/effects';
import { CertificatesAdminEffects } from './store/certificates-admin.effects';

@NgModule({
  declarations: [
    CertificatesAdminComponent,
    CsrPendingTableComponent,
    NewCertificateFormComponent,
    AllCertificatesTableComponent,
  ],
  imports: [
    CertificatesAdminRoutingModule,
    SharedModule,
    StoreModule.forFeature('security_admin', fromCertificatesAdmin.reducer),
    EffectsModule.forFeature([CertificatesAdminEffects]),
  ],
})
export class CertificatesAdminModule {}
