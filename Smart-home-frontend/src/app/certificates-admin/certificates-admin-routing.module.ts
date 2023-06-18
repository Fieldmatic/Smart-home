import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CertificatesAdminComponent } from './components/certificates-admin/certificates-admin.component';
import { CsrPendingTableComponent } from './components/certificates-admin/csr-pending-table/csr-pending-table.component';
import { NewCertificateFormComponent } from './components/certificates-admin/new-certificate-form/new-certificate-form.component';
import { CsrResolver } from './resolvers/csr.resolver';
import { CertificateTypesResolver } from './resolvers/certificate-types.resolver';
import { CertificateExtensionsResolver } from './resolvers/certificate-extensions.resolver';
import { AllCertificatesTableComponent } from './components/certificates-admin/all-certificates-table/all-certificates-table.component';
import { CertificatesResolver } from './resolvers/certificates.resolver';
import { CsrsResolver } from './resolvers/csrs.resolver';

const routes: Routes = [
  {
    path: '',
    component: CertificatesAdminComponent,
    children: [
      {
        path: 'csr/pending',
        component: CsrPendingTableComponent,
        resolve: [CsrsResolver],
      },
      {
        path: 'certificate/:id/new',
        component: NewCertificateFormComponent,
        resolve: [
          CsrResolver,
          CertificateTypesResolver,
          CertificateExtensionsResolver,
        ],
      },
      {
        path: 'certificate/all',
        component: AllCertificatesTableComponent,
        resolve: [CertificatesResolver],
      },
    ],
  },
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class CertificatesAdminRoutingModule {}
