import { createFeatureSelector, createSelector } from '@ngrx/store';
import { State } from './certificates-admin.reducer';

export const selectCertificatesAdmin =
  createFeatureSelector<State>('security-admin');

export const selectCSRs = createSelector(
  selectCertificatesAdmin,
  (state) => state.csrs
);

export const selectCertificateTypes = createSelector(
  selectCertificatesAdmin,
  (state) => state.certificateTypes
);

export const selectCertificateExtensions = createSelector(
  selectCertificatesAdmin,
  (state) => state.certificateExtensions
);

export const selectCSR = (id: string) =>
  createSelector(selectCSRs, (csrs) => csrs.find((csr) => csr.id === id));

export const selectCertificates = createSelector(
  selectCertificatesAdmin,
  (state) => state.certificates
);

export const selectCertificate = (index: number) =>
  createSelector(selectCertificates, (certificates) => certificates[index]);
