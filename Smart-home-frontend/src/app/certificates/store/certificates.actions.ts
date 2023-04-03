import { createAction, props, union } from '@ngrx/store';

export const createCSR = createAction(
  '[Certificates] Create CSR',
  props<{
    commonName: string;
    organization: string;
    organizationalUnit: string;
    city: string;
    state: string;
    country: string;
    keySize: number;
    algorithm: string;
  }>()
);

export const createCSRSuccess = createAction(
  '[Certificates] Create CSR Success'
);

const all = union({
  createCSR,
  createCSRSuccess,
});

export type CertificatesActionsUnion = typeof all;
