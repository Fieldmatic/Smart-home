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
  on(CertificatesAdminActions.setCSRs, (state, { csrs }) => ({
    ...state,
    csrs,
  })),
  on(CertificatesAdminActions.rejectCSRSuccess, (state, { id }) => {
    const updatedCsrs = state.csrs.filter((csr) => csr.id !== id);
    return { ...state, csrs: updatedCsrs };
  }),
  on(
    CertificatesAdminActions.setCertificateTypes,
    (state, { certificateTypes }) => ({
      ...state,
      certificateTypes,
    })
  ),
  on(
    CertificatesAdminActions.setCertificateExtensions,
    (state, { certificateExtensions }) => ({
      ...state,
      certificateExtensions,
    })
  ),
  on(CertificatesAdminActions.setCertificates, (state, { certificates }) => ({
    ...state,
    certificates,
  })),
  on(CertificatesAdminActions.revokeCertificateSuccess, (state, { alias }) => {
    const updatedCertificates = state.certificates.map((certificate) =>
      certificate.email === alias
        ? { ...certificate, isValid: false }
        : certificate
    );
    return { ...state, certificates: updatedCertificates };
  })
);

export function reducer(state: State | undefined, action: Action) {
  return authReducer(state, action);
}
