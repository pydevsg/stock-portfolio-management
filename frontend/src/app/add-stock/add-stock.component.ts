import { FormControl, FormGroup, Validators } from '@angular/forms';
import { StockService } from '../stock.service';

export class AddStockComponent {

  stockForm = new FormGroup({
    symbol: new FormControl('', [Validators.required]),
    quantity: new FormControl(1, [Validators.required, Validators.min(1)]),
    price: new FormControl(0, [Validators.required, Validators.min(0.01)])
  });

  constructor(private stockService: StockService) {}

  onSubmit() {
    const stock = {
      symbol: this.stockForm.value.symbol || '',
      quantity: this.stockForm.value.quantity || 0,
      price: this.stockForm.value.price || 0
    };
    this.stockService.addStock(stock).subscribe(() => {
      alert('Stock added!');
    });
  }
}
