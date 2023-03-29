import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CertificatesComponent } from './components/certificates/certificates.component';
import { NewCsrFormComponent } from './components/certificates/new-csr-form/new-csr-form.component';
import { NewCertificateFormComponent } from './components/certificates/new-certificate-form/new-certificate-form.component';

const routes: Routes = [
  {
    path: '',
    component: CertificatesComponent,
    children: [
      {
        path: 'csr/new',
        component: NewCsrFormComponent,
      },
      {
        path: 'certificate/new',
        component: NewCertificateFormComponent,
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class CertificatesRoutingModule {}
