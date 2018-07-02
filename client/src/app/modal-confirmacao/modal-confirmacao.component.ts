import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';

@Component({
  selector: 'app-modal-confirmacao',
  templateUrl: './modal-confirmacao.component.html',
  styleUrls: ['./modal-confirmacao.component.css']
})
export class ModalConfirmacaoComponent implements OnInit {
  message: string;

  constructor(public dialogRef: MatDialogRef<ModalConfirmacaoComponent>,
              @Inject(MAT_DIALOG_DATA) public data: string) {
  }

  cancelar(): void {
    this.dialogRef.close();
  }

  confirmar() {
    this.dialogRef.close(true);
  }

  ngOnInit() {
    console.log(this.data);
    this.message = this.data;
  }

}
