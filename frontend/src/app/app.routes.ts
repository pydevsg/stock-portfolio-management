import { Routes } from '@angular/router';
import { StockListComponent } from './stock-list/stock-list.component';
import { AddStockComponent } from './add-stock/add-stock.component';

export const routes: Routes = [ 
    { path: '', component: StockListComponent },
    { path: 'add-stock', component: AddStockComponent }
];