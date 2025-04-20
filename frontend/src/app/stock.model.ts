export interface Stock {
    id?: string;       // Make optional if your backend might not return it
    symbol: string;
    price: number;
    quantity: number;
    currentPrice?: number;  
  }