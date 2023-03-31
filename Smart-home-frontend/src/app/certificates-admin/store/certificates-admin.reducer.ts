import { Action, createReducer, on } from '@ngrx/store';
import * as CertificatesAdminActions from './certificates-admin.actions';
import { CSR } from '../model/CSR.model';
import { CertificateType } from '../model/certificate-type.model';
import { Extension } from '../model/extension.model';

export interface State {
  csrs: CSR[];
  certificateTypes: CertificateType[];
  certificateExtensions: Extension[];
}

const initialState: State = {
  csrs: [],
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
  )
);

export function reducer(state: State | undefined, action: Action) {
  return authReducer(state, action);
}
