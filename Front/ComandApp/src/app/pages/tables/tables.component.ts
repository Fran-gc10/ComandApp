import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import Table from 'src/app/models/table.model';
import { TableService } from 'src/app/services/table/table.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-tables',
  templateUrl: './tables.component.html',
  styleUrls: ['./tables.component.scss']
})
export class TablesComponent {
  
  public tables$: Observable<Table[]>
  public tablePath = environment.host 

  public constructor(private tableService: TableService) {
    this.tables$ = this.tableService.getTables();
  }

}
