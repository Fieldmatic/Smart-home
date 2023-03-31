import { Injectable } from '@angular/core';
import { NotifierService } from '../../core/notifier.service';
import { Router } from '@angular/router';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { map, switchMap, tap } from 'rxjs';
import { CertificatesHttpService } from '../services/certificates-http.service';
import * as CertificatesActions from './certificates.actions';

@Injectable()
export class CertificatesEffects {
  create_csr = createEffect(() => {
    return this.actions$.pipe(
      ofType(CertificatesActions.create_csr.type),
      switchMap((action) => {
        return this.httpService
          .sendCreateCSRRequest(
            action.commonName,
            action.organization,
            action.organizationalUnit,
            action.city,
            action.state,
            action.country,
            action.keySize,
            action.algorithm
          )
          .pipe(
            map(() => {
              return CertificatesActions.create_csr_success();
            })
          );
      })
    );
  });

  create_csr_success = createEffect(
    () => {
      return this.actions$.pipe(
        ofType(CertificatesActions.create_csr_success.type),
        tap(() => {
          this.router.navigate(['/']);
        }),
        map(() => {
          const message = 'You have successfully created a CSR.';
          this.notifierService.notifySuccess(message);
        })
      );
    },
    { dispatch: false }
  );

  constructor(
    private notifierService: NotifierService,
    private router: Router,
    private actions$: Actions<CertificatesActions.CertificatesActionsUnion>,
    private httpService: CertificatesHttpService
  ) {}
}
