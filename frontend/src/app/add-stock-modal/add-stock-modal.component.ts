import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { StockService } from '../stock.service';

@Component({
  selector: 'app-add-stock-modal',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule
  ],
  templateUrl: './add-stock-modal.component.html',
  styleUrls: ['./add-stock-modal.component.css']
})
export class AddStockModalComponent {
  stockForm = new FormGroup({
    symbol: new FormControl('', [Validators.required]),
    quantity: new FormControl(1, [Validators.required, Validators.min(1)]),
    price: new FormControl(0, [Validators.required, Validators.min(0.01)])
  });

  constructor(
    private dialogRef: MatDialogRef<AddStockModalComponent>,
    private stockService: StockService
  ) {}

  onSubmit() {
    if (this.stockForm.valid) {
      this.stockService.addStock(this.stockForm.value as {
        symbol: string;
        quantity: number;
        price: number;
      }).subscribe({
        next: () => this.dialogRef.close(true),
        error: (err) => console.error('Error adding stock', err)
      });
    }
  }

  onCancel() {
    this.dialogRef.close(false);
  }
}