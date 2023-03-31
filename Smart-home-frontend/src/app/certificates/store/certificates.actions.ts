import { createAction, props, union } from '@ngrx/store';

export const create_csr = createAction(
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

export const create_csr_success = createAction(
  '[Certificates] Create CSR Success'
);

const all = union({
  create_csr,
  create_csr_success,
});

export type CertificatesActionsUnion = typeof all;
