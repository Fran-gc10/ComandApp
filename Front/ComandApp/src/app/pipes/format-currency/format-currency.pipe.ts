import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'formatCurrency'
})
export class FormatCurrencyPipe implements PipeTransform {

  transform(value: number): string {
    return value.toLocaleString('es-ES', {
      style: 'currency',
      currency: 'EUR',
    });
  }

}
