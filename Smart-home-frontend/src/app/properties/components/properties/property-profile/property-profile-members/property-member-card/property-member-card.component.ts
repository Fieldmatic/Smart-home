import { Component, Input } from '@angular/core';
import { User } from '../../../../../../shared/model/user.model';
import { Store } from '@ngrx/store';
import { removePropertyMember } from '../../../../../store/properties.actions';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmationDialogComponent } from '../../../../../../shared/components/confirmation-dialog/confirmation-dialog.component';

@Component({
  selector: 'app-property-member-card',
  templateUrl: './property-member-card.component.html',
  styleUrls: ['./property-member-card.component.scss'],
})
export class PropertyMemberCardComponent {
  @Input() propertyId!: string;
  @Input() member!: User;
  @Input() isOwner!: boolean;

  constructor(private store: Store, private dialog: MatDialog) {}

  removeMember() {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: {
        title: 'Property Member Removal',
        text: `Are you sure you want to remove the property member?`,
      },
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.store.dispatch(
          removePropertyMember({
            propertyId: this.propertyId,
            userId: this.member.id,
          })
        );
      }
    });
  }
}
