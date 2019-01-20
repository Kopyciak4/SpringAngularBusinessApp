import { Component, OnInit, ViewEncapsulation} from '@angular/core';
import { User } from '../../models/user';
import { AuthorizationService } from '../../services/authorization.service';
import { Account } from '../../models/account';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';
 


@Component({
  selector: 'app-employees-page',
  templateUrl: './employees-page.component.html',
	styleUrls: ['./employees-page.component.scss'],
	encapsulation: ViewEncapsulation.None
})


export class EmployeesPageComponent implements OnInit {

	private columnDefs = [];
	private rowData = [];
	private rowSelection = "single";
	private gridColumnApi;
	private gridApi;
	private selectedLogin;
	


	login: string;
	accountExists: boolean;
	

  constructor(
		private auth: AuthorizationService,
		private modalService: NgbModal,
		private router: Router,
		
  ) { 
		this.auth.getAccounts().subscribe((response: Account[])=> {
			let row;
			for (let i = 0; i<response.length; i++){ 
				 	row  = {
						login: response[i].user.login,
						name: response[i].name,
						surname: response[i].surname,
						email: response[i].email,
						adress: response[i].adress,
						accountid: response[i].accountID
				}  
				this.rowData.push(row);
				
			}
			this.columnDefs = Object.keys(row).map(key => {
				
				return {
					headerName: key.toUpperCase(),
					field: key
				}
			});
			console.log(this.rowData); 
		},
		err => {
			console.log("err"); 
		});
	}

  ngOnInit() {
	
	} 
	
	onFirstDataRendered(params) {
		params.api.sizeColumnsToFit();
	}

	openModal(modal){
		this.modalService.open(modal).result.then((result)=> {
			this.router.navigate(['profile', this.login]);
		},(resoult) =>{
			
		}  
		);
	}

	modelChanged() {
		this.accountExists = false;
		for(let i=0; i<this.rowData.length; i++){
			if(this.rowData[i].login === this.login){
				this.accountExists = true;
			}
		}

		//this.accountExists = this.rowData.some((row) => row.login === this.login);	 
	}

	onSelectionChanged() {
		this.selectedLogin = this.gridApi.getSelectedRows()[0].login;
		console.log(this.selectedLogin);
	}

	
	onGridReady(params) {
		this.gridApi = params.api;
	}

	editProfile() {
		this.router.navigate(['profile',this.selectedLogin]);
	}

	deleteProfile() {
		let accountToDelete = this.gridApi.getSelectedRows()[0];
		this.auth.deleteAccount(accountToDelete.accountid).subscribe(res => {
			console.log(accountToDelete);
			console.log(this.rowData.indexOf(accountToDelete));
			//this.rowData = this.rowData.splice(this.rowData.indexOf(accountToDelete), 1); 
			this.rowData = this.rowData.filter((row) => row.login != this.selectedLogin); 
			this.selectedLogin = null;
		});

	}
	

}
