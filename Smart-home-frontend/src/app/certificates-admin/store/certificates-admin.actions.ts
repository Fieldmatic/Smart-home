import { createAction, props, union } from '@ngrx/store';
import { CSR } from '../model/CSR.model';
import { CertificateType } from '../model/certificate-type.model';
import { Extension } from '../model/extension.model';
import { Capabilities } from '../model/capabilities.model';
import { Certificate } from '../model/certificate.model';

export const getPendingCSRs = createAction(
  '[Certificates Admin] Get Pending CSRs'
);

export const setCSRs = createAction(
  '[Certificates Admin] Set CSRs',
  props<{ csrs: CSR[] }>()
);

export const rejectCSR = createAction(
  '[Certificates Admin] Reject CSR',
  props<{ id: string; reason: string }>()
);

export const rejectCSRSuccess = createAction(
  '[Certificates Admin] Reject CSR Success',
  props<{ id: string }>()
);

export const getCertificateTypes = createAction(
  '[Certificates Admin] Get Certificate Types'
);

export const setCertificateTypes = createAction(
  '[Certificates Admin] Set Certificate Types',
  props<{ certificateTypes: CertificateType[] }>()
);

export const getCertificateExtensions = createAction(
  '[Certificates Admin] Get Certificate Extensions'
);

export const setCertificateExtensions = createAction(
  '[Certificates Admin] Set Certificate Extensions',
  props<{ certificateExtensions: Extension[] }>()
);

export const createCertificate = createAction(
  '[Certificates Admin] Create Certificate',
  props<{
    capabilities: Capabilities[];
    start: Date;
    end: Date;
    csrId: string;
    serialNumber: string;
    caAlias: string;
  }>()
);

export const createCertificateSuccess = createAction(
  '[Certificates Admin] Create Certificate Success'
);

export const getCertificates = createAction(
  '[Certificates Admin] Get Certificates'
);

export const setCertificates = createAction(
  '[Certificates Admin] Set Certificates',
  props<{ certificates: Certificate[] }>()
);

export const revokeCertificate = createAction(
  '[Certificates Admin] Revoke Certificate',
  props<{ alias: string; message: string; reason: number }>()
);

export const revokeCertificateSuccess = createAction(
  '[Certificates Admin] Revoke Certificate Success',
  props<{ alias: string }>()
);

const all = union({
  getPendingCSRs,
  setCSRs,
  rejectCSR,
  rejectCSRSuccess,
  getCertificateTypes,
  setCertificateTypes,
  getCertificateExtensions,
  setCertificateExtensions,
  createCertificate,
  createCertificateSuccess,
  getCertificates,
  setCertificates,
  revokeCertificate,
  revokeCertificateSuccess,
});

export type CertificatesAdminActionsUnion = typeof all;
