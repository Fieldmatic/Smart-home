import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { map, switchMap, tap } from 'rxjs';
import { Router } from '@angular/router';
import { CertificatesAdminHttpService } from '../services/certificates-admin-http.service';
import * as CertificatesAdminActions from './certificates-admin.actions';
import { NotifierService } from '../../core/notifier.service';

@Injectable()
export class CertificatesAdminEffects {
  get_pending_csrs = createEffect(() => {
    return this.actions$.pipe(
      ofType(CertificatesAdminActions.getPendingCSRs.type),
      switchMap(() => {
        return this.httpService.getPendingCSRs().pipe(
          map((csrs) => {
            return CertificatesAdminActions.setCSRs({ csrs });
          })
        );
      })
    );
  });

  reject_csr = createEffect(() => {
    return this.actions$.pipe(
      ofType(CertificatesAdminActions.rejectCSR.type),
      switchMap((action) => {
        return this.httpService.rejectCSR(action.id, action.reason).pipe(
          map(() => {
            return CertificatesAdminActions.rejectCSRSuccess({
              id: action.id,
            });
          })
        );
      })
    );
  });

  reject_csr_success = createEffect(
    () => {
      return this.actions$.pipe(
        ofType(CertificatesAdminActions.rejectCSRSuccess.type),
        map((action) => {
          const message =
            'You have successfully rejected CSR with ID ' + action.id + '.';
          this.notifierService.notifySuccess(message);
        })
      );
    },
    { dispatch: false }
  );

  get_certificate_types = createEffect(() => {
    return this.actions$.pipe(
      ofType(CertificatesAdminActions.getCertificateTypes.type),
      switchMap(() => {
        return this.httpService.getCertificateTypes().pipe(
          map((certificateTypes) => {
            return CertificatesAdminActions.setCertificateTypes({
              certificateTypes,
            });
          })
        );
      })
    );
  });

  get_certificate_extensions = createEffect(() => {
    return this.actions$.pipe(
      ofType(CertificatesAdminActions.getCertificateExtensions.type),
      switchMap(() => {
        return this.httpService.getCertificateExtensions().pipe(
          map((certificateExtensions) => {
            return CertificatesAdminActions.setCertificateExtensions({
              certificateExtensions,
            });
          })
        );
      })
    );
  });

  create_certificate = createEffect(() => {
    return this.actions$.pipe(
      ofType(CertificatesAdminActions.createCertificate.type),
      switchMap((action) => {
        return this.httpService
          .sendCreateCertificateRequest(
            action.capabilities,
            action.start,
            action.end,
            action.csrId,
            action.serialNumber,
            action.caAlias
          )
          .pipe(
            map(() => {
              return CertificatesAdminActions.createCertificateSuccess();
            })
          );
      })
    );
  });

  create_certificate_success = createEffect(
    () => {
      return this.actions$.pipe(
        ofType(CertificatesAdminActions.createCertificateSuccess.type),
        tap(() => {
          this.router.navigate(['/admin/security/csr/pending']);
        }),
        map(() => {
          const message = 'You have successfully created a certificate.';
          this.notifierService.notifySuccess(message);
        })
      );
    },
    { dispatch: false }
  );

  get_certificates = createEffect(() => {
    return this.actions$.pipe(
      ofType(CertificatesAdminActions.getCertificates.type),
      switchMap(() => {
        return this.httpService.getCertificates().pipe(
          map((certificates) => {
            return CertificatesAdminActions.setCertificates({ certificates });
          })
        );
      })
    );
  });

  delete_certificate = createEffect(() => {
    return this.actions$.pipe(
      ofType(CertificatesAdminActions.deleteCertificate.type),
      switchMap((action) => {
        return this.httpService.deleteCertificate(action.alias).pipe(
          map(() => {
            return CertificatesAdminActions.deleteCertificateSuccess({
              alias: action.alias,
            });
          })
        );
      })
    );
  });

  delete_certificate_success = createEffect(
    () => {
      return this.actions$.pipe(
        ofType(CertificatesAdminActions.deleteCertificateSuccess.type),
        map((action) => action.alias),
        tap((alias) => {
          const message =
            'You have successfully delete certificate for user ' + alias + '.';
          this.notifierService.notifySuccess(message);
        })
      );
    },
    { dispatch: false }
  );

  constructor(
    private notifierService: NotifierService,
    private router: Router,
    private actions$: Actions<CertificatesAdminActions.CertificatesAdminActionsUnion>,
    private httpService: CertificatesAdminHttpService
  ) {}
}
