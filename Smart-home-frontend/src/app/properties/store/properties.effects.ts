import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { NotifierService } from '../../core/notifier.service';
import { Router } from '@angular/router';
import * as PropertiesActions from '../store/properties.actions';
import { map, switchMap } from 'rxjs';
import { PropertiesHttpService } from '../services/properties-http.service';
import { AddressHttpService } from '../services/address-http.service';

@Injectable()
export class PropertiesEffects {
  getUserProperties = createEffect(() => {
    return this.actions$.pipe(
      ofType(PropertiesActions.getUserProperties.type),
      switchMap((action) => {
        return this.httpService
          .getUserProperties(action.id)
          .pipe(
            map((properties) =>
              PropertiesActions.setUserProperties({ properties })
            )
          );
      })
    );
  });

  getSelfProperties = createEffect(() => {
    return this.actions$.pipe(
      ofType(PropertiesActions.getSelfProperties.type),
      switchMap(() => {
        return this.httpService
          .getSelfProperties()
          .pipe(
            map((properties) =>
              PropertiesActions.setUserProperties({ properties })
            )
          );
      })
    );
  });

  createProperty = createEffect(() => {
    return this.actions$.pipe(
      ofType(PropertiesActions.createProperty.type),
      switchMap((action) => {
        return this.httpService
          .createProperty(action.name, action.address, action.ownerId)
          .pipe(
            map((property) =>
              PropertiesActions.createPropertySuccess({ property })
            )
          );
      })
    );
  });

  deleteProperty = createEffect(() => {
    return this.actions$.pipe(
      ofType(PropertiesActions.deleteProperty.type),
      switchMap((action) => {
        return this.httpService.deleteProperty(action.id).pipe(
          map(() =>
            PropertiesActions.deletePropertySuccess({
              propertyId: action.id,
              ownerId: action.ownerId,
            })
          )
        );
      })
    );
  });

  addPropertyMember = createEffect(() => {
    return this.actions$.pipe(
      ofType(PropertiesActions.addPropertyMember.type),
      switchMap((action) => {
        return this.httpService
          .addPropertyMember(action.userId, action.propertyId)
          .pipe(
            map((property) =>
              PropertiesActions.addPropertyMemberSuccess({ property })
            )
          );
      })
    );
  });

  removePropertyMember = createEffect(() => {
    return this.actions$.pipe(
      ofType(PropertiesActions.removePropertyMember.type),
      switchMap((action) => {
        return this.httpService
          .removePropertyMember(action.userId, action.propertyId)
          .pipe(
            map((property) =>
              PropertiesActions.removePropertyMemberSuccess({ property })
            )
          );
      })
    );
  });

  createPropertySuccess = createEffect(
    () => {
      return this.actions$.pipe(
        ofType(PropertiesActions.createPropertySuccess.type),
        map((action) => {
          const message = 'You have successfully created a property.';
          this.notifierService.notifySuccess(message);
          this.router.navigate([
            '/admin/users/user/' + action.property.owner.id,
          ]);
        })
      );
    },
    { dispatch: false }
  );

  deletePropertySuccess = createEffect(
    () => {
      return this.actions$.pipe(
        ofType(PropertiesActions.deletePropertySuccess.type),
        map((action) => {
          const message = 'You have successfully deleted the property.';
          this.notifierService.notifySuccess(message);
          this.router.navigate(['/admin/users/user/' + action.ownerId]);
        })
      );
    },
    { dispatch: false }
  );

  addPropertyMemberSuccess = createEffect(
    () => {
      return this.actions$.pipe(
        ofType(PropertiesActions.addPropertyMemberSuccess.type),
        map((action) => {
          const message =
            'You have successfully added a member to the property.';
          this.notifierService.notifySuccess(message);
        })
      );
    },
    { dispatch: false }
  );

  removePropertyMemberSuccess = createEffect(
    () => {
      return this.actions$.pipe(
        ofType(PropertiesActions.removePropertyMemberSuccess.type),
        map((action) => {
          const message =
            'You have successfully removed a member from the property.';
          this.notifierService.notifySuccess(message);
        })
      );
    },
    { dispatch: false }
  );

  loadDestinationAutocompleteResults = createEffect(() =>
    this.actions$.pipe(
      ofType(PropertiesActions.searchAddress.type),
      switchMap((action) => {
        return this.addressHttpService
          .getAddressSearchResults(action.value)
          .pipe(
            map((result) => {
              const searchAddressResults: string[] = result.results.map(
                (locationObject) => locationObject.formatted
              );
              return PropertiesActions.setSearchAddressResult({
                searchAddressResults,
              });
            })
          );
      })
    )
  );

  constructor(
    private notifierService: NotifierService,
    private router: Router,
    private actions$: Actions<PropertiesActions.PropertiesActionsUnion>,
    private httpService: PropertiesHttpService,
    private addressHttpService: AddressHttpService
  ) {}
}
