import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog } from '@angular/material/dialog';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner'; // Add this import
import { AddStockModalComponent } from '../add-stock-modal/add-stock-modal.component';
import { StockService } from '../stock.service';
import { Stock } from '../stock.model';

@Component({
  selector: 'app-stock-list',
  standalone: true,
  imports: [
    CommonModule,
    MatCardModule,
    MatButtonModule,
    MatProgressSpinnerModule // Add this to imports
  ],
  templateUrl: './stock-list.component.html',
  styleUrls: ['./stock-list.component.css']
})
export class StockListComponent {
  stocks: Stock[] = [
    { symbol: 'AAPL', price: 0, quantity: 0 },
    { symbol: 'GOOGL', price: 0, quantity: 0 },
    { symbol: 'AMZN', price: 0, quantity: 0 }
  ];
  isLoading = true;

  constructor(
    private stockService: StockService,
    private dialog: MatDialog
  ) {
    this.loadStocks();
  }

  loadStocks() {
    this.isLoading = true;
    this.stockService.getStocks().subscribe({
      next: (apiStocks) => {
        if (apiStocks?.length) {
          this.stocks = apiStocks;
        }
        this.isLoading = false;
      },
      error: (err) => {
        console.error('Error loading stocks', err);
        this.isLoading = false;
      }
    });
  }

  addStock() {
    this.openAddStockModal();
  }

  openAddStockModal() {
    const dialogRef = this.dialog.open(AddStockModalComponent, {
      width: '400px'
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.loadStocks(); // Refresh with API data
      }
    });
  }

  removeStock(id: string) {
    this.stockService.removeStock(id).subscribe({
      next: () => this.loadStocks(),
      error: (err) => console.error('Error removing stock', err)
    });
  }
}