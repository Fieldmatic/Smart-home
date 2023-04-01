import { Action, createReducer, on } from '@ngrx/store';
import * as CertificatesAdminActions from './certificates-admin.actions';
import { CSR } from '../model/CSR.model';
import { CertificateType } from '../model/certificate-type.model';
import { Extension } from '../model/extension.model';
import { Certificate } from '../model/certificate.model';

export interface State {
  csrs: CSR[];
  certificates: Certificate[];
  certificateTypes: CertificateType[];
  certificateExtensions: Extension[];
}

const initialState: State = {
  csrs: [],
  certificates: [],
  certificateTypes: [],
  certificateExtensions: [],
};

const authReducer = createReducer(
  initialState,
  on(CertificatesAdminActions.set_csrs, (state, { csrs }) => ({
    ...state,
    csrs,
  })),
  on(CertificatesAdminActions.reject_csr_success, (state, { id }) => {
    const updatedCsrs = state.csrs.filter((csr) => csr.id !== id);
    return { ...state, csrs: updatedCsrs };
  }),
  on(
    CertificatesAdminActions.set_certificate_types,
    (state, { certificateTypes }) => ({
      ...state,
      certificateTypes,
    })
  ),
  on(
    CertificatesAdminActions.set_certificate_extensions,
    (state, { certificateExtensions }) => ({
      ...state,
      certificateExtensions,
    })
  ),
  on(CertificatesAdminActions.set_certificates, (state, { certificates }) => ({
    ...state,
    certificates,
  })),
  on(
    CertificatesAdminActions.delete_certificate_success,
    (state, { alias }) => {
      const updatedCertificates = state.certificates.filter(
        (certificate) => certificate.email !== alias
      );
      return { ...state, certificates: updatedCertificates };
    }
  )
);

export function reducer(state: State | undefined, action: Action) {
  return authReducer(state, action);
}
