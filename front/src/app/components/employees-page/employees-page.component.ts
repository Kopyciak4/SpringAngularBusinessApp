import { Component, OnInit, ViewEncapsulation} from '@angular/core';


@Component({
  selector: 'app-employees-page',
  templateUrl: './employees-page.component.html',
	styleUrls: ['./employees-page.component.scss'],
	encapsulation: ViewEncapsulation.None
})


export class EmployeesPageComponent implements OnInit {

  columnDefs = [
		{headerName: 'Make', field: 'make', width: 400 },
		{headerName: 'Model', field: 'model' },
		{headerName: 'Price', field: 'price'},
		
	
	];

	rowData = [
		{ make: 'Toyota', model: 'Celica', price: 35000 },
		{ make: 'Ford', model: 'Mondeo', price: 32000 },
		{ make: 'Porsche', model: 'Boxter', price: 72000 }
	];

	
  constructor() { }

  ngOnInit() {
  }

}
