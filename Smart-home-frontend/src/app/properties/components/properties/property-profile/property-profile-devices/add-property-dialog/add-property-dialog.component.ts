import { Component, Inject, OnInit } from "@angular/core";
import { MAT_DIALOG_DATA } from "@angular/material/dialog";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";

@Component({
  selector: 'app-add-property-dialog',
  templateUrl: './add-property-dialog.component.html',
  styleUrls: ['./add-property-dialog.component.scss']
})
export class AddPropertyDialogComponent implements OnInit {
  dialogForm: FormGroup;
  options: string[] = ['Opcija 1', 'Opcija 2', 'Opcija 3'];
  constructor(
    private formBuilder: FormBuilder,
    @Inject(MAT_DIALOG_DATA)
    public data: { name: string; title: string; text: string }
  ) {
    this.dialogForm = this.formBuilder.group({
      selectedOption: ['', Validators.required],
      firstInput: ['', Validators.required],
      secondInput: ['', Validators.required]
    });
  }

  ngOnInit() {
    console.log()
  }
}
