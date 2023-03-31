import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CertificatesAdminComponent } from './components/certificates-admin/certificates-admin.component';
import { CsrPendingTableComponent } from './components/certificates-admin/csr-pending-table/csr-pending-table.component';
import { NewCertificateFormComponent } from './components/certificates-admin/new-certificate-form/new-certificate-form.component';
import { CsrResolver } from './resolvers/csr.resolver';
import { CertificateTypesResolver } from './resolvers/certificate-types.resolver';
import { CertificateExtensionsResolver } from './resolvers/certificate-extensions.resolver';

const routes: Routes = [
  {
    path: '',
    component: CertificatesAdminComponent,
    children: [
      {
        path: 'csr/pending',
        component: CsrPendingTableComponent,
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
    ],
  },
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class CertificatesAdminRoutingModule {}
