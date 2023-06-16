import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FormMode } from '../../../../shared/model/form-mode';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import {
  debounceTime,
  distinctUntilChanged,
  filter,
  Observable,
  Subscription,
  tap,
} from 'rxjs';
import { Store } from '@ngrx/store';
import {
  createProperty,
  deleteProperty,
  searchAddress,
} from '../../../store/properties.actions';
import {
  selectAddressSearchResults,
  selectPropertyWithId,
} from '../../../store/properties.selectors';
import { ConfirmationDialogComponent } from '../../../../shared/components/confirmation-dialog/confirmation-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { textValidator } from '../../../../shared/validators/text.validator';

@Component({
  selector: 'app-property-profile-form',
  templateUrl: './property-profile-form.component.html',
  styleUrls: ['./property-profile-form.component.scss'],
})
export class PropertyProfileFormComponent implements OnInit, OnDestroy {
  mode: FormMode = FormMode.NEW;
  ownerId!: string;
  propertyId!: string;
  addresses$!: Observable<Array<string>>;
  propertyProfileForm!: FormGroup<{
    name: FormControl;
    address: FormControl;
    addressSelection: FormControl;
  }>;
  private storeSubscription!: Subscription;

  constructor(
    private route: ActivatedRoute,
    private store: Store,
    private dialog: MatDialog
  ) {}

  ngOnInit() {
    const mode = this.route.snapshot.url[0].path;
    this.addresses$ = this.store.select(selectAddressSearchResults);
    let name = '';
    let address = '';
    if (mode === 'new') {
      this.mode = FormMode.NEW;
      this.ownerId = this.route.snapshot.params['id'];
    } else {
      this.mode = FormMode.EDIT;
      this.propertyId = this.route.snapshot.params['id'];
      this.storeSubscription = this.store
        .select(selectPropertyWithId(this.propertyId))
        .subscribe((property) => {
          name = property.name;
          address = property.address;
          this.ownerId = property.owner.id;
        });
    }
    this.propertyProfileForm = new FormGroup({
      name: new FormControl(name, [Validators.required, textValidator]),
      address: new FormControl(address, [Validators.required]),
      addressSelection: new FormControl(address, [Validators.required]),
    });
    this.initAddressesAutocompleteOnChange();
  }

  ngOnDestroy() {
    this.storeSubscription?.unsubscribe();
  }

  protected readonly FormMode = FormMode;

  private initAddressesAutocompleteOnChange() {
    this.propertyProfileForm.controls['address'].valueChanges
      .pipe(
        debounceTime(25),
        distinctUntilChanged(),
        tap((value: string) => {
          if (value.length === 0) {
            this.propertyProfileForm.get('addressSelection')?.setValue(value);
          }
        }),
        filter((value: string) => value.length > 0)
      )
      .subscribe((value: string) => {
        this.store.dispatch(searchAddress({ value: value }));
      });
  }

  submitPropertyForm() {
    if (this.propertyProfileForm.valid) {
      const name = this.propertyProfileForm.controls['name'].value;
      const address =
        this.propertyProfileForm.controls['addressSelection'].value;
      if (this.mode === FormMode.NEW) {
        const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
          data: {
            title: 'Property Creation',
            text: `Are you sure you want to create a new property?`,
          },
        });

        dialogRef.afterClosed().subscribe((result) => {
          if (result) {
            this.store.dispatch(
              createProperty({ name, address, ownerId: this.ownerId })
            );
          }
        });
      }
    }
  }

  deleteProperty($event: MouseEvent) {
    this.storeSubscription.unsubscribe();
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: {
        title: 'Property Deletion',
        text: `Are you sure you want to delete the property?`,
      },
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.store.dispatch(
          deleteProperty({ id: this.propertyId, ownerId: this.ownerId })
        );
      }
    });

    $event.preventDefault();
  }
}
