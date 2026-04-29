import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'category',
  standalone: true,
})
export class CategoryPipe implements PipeTransform {
  transform(value: string): string {
    switch (value) {
      case 'No.':
        return 'format_list_numbered_rtl';
      case 'Name':
        return 'science';
      case 'Weight':
        return 'balance';
      case 'Symbol':
        return 'token';

      default:
        return 'help_outline';
    }
  }
}
