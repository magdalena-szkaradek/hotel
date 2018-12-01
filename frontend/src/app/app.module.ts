import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { RoomComponentComponent } from './room-component/room-component.component';
import { HeaderComponentComponent } from './header-component/header-component.component';
import { FooterComponent } from './footer/footer.component';
import { SliderComponentComponent } from './slider-component/slider-component.component';
import { SearchComponentComponent } from './search-component/search-component.component';
import {RouterModule, Routes} from '@angular/router';
import {HttpModule} from '@angular/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { MainComponentComponent } from './components/main-component/main-component.component';
import {APP_BASE_HREF} from '@angular/common';
import { UserService } from './services/user.service';
import { ReservationService } from './services/reservation.service';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';
import { AdminClientsComponent } from './components/admin-clients/admin-clients.component';
import { AdminEmployeesComponent } from './components/admin-employees/admin-employees.component';
import { AdminNavbarComponent } from './components/admin-navbar/admin-navbar.component';
import { AdminAddEmployeeComponent } from './components/admin-add-employee/admin-add-employee.component';
import { AdminAddClientComponent } from './components/admin-add-client/admin-add-client.component';
import { AdminRoomsComponent } from './components/admin-rooms/admin-rooms.component';
import { AdminReservationsComponent } from './components/admin-reservations/admin-reservations.component';
import { AdminPricesComponent } from './components/admin-prices/admin-prices.component';
import { AdminAddRoomComponent } from './components/admin-add-room/admin-add-room.component';
import { AdminAddPricesComponent } from './components/admin-add-price/admin-add-price.component';
import { RoomService } from './services/room.service';
import { CustomFormsModule } from 'ng2-validation';
import { EmployeeProfileComponent } from './components/employee-profile/employee-profile.component';
import { EmployeeAllClientsComponent } from './components/employee-all-clients/employee-all-clients.component'


const appRoutes: Routes = [
  {path:'', component: MainComponentComponent},
  {path:'register', component: RegisterComponent},
  {path:'login', component: LoginComponent},
  {path:'logout', component: LogoutComponent},
  {path:'admin/clients', component: AdminClientsComponent},
  {path:'admin/employees', component: AdminEmployeesComponent},
  {path:'admin/prices', component: AdminPricesComponent},
  {path:'admin/reservations', component: AdminReservationsComponent},
  {path:'admin/add-employee', component: AdminAddEmployeeComponent},
  {path:'admin/add-client', component: AdminAddClientComponent},
  {path:'admin/add-price', component: AdminAddPricesComponent},
  {path:'admin/rooms', component: AdminRoomsComponent},
  {path:'admin/add-room', component: AdminAddRoomComponent},
  {path:'employeeProfile', component: EmployeeProfileComponent},
  {path:'employeeProfile/employeeAllClients', component: EmployeeAllClientsComponent}
]

@NgModule({
  declarations: [
    AppComponent,
    RoomComponentComponent,
    HeaderComponentComponent,
    FooterComponent,
    SliderComponentComponent,
    SearchComponentComponent,
    MainComponentComponent,
    RegisterComponent,
    LoginComponent,
    LogoutComponent,
    AdminClientsComponent,
    AdminPricesComponent,
    AdminReservationsComponent,
    AdminNavbarComponent,
    AdminAddEmployeeComponent,
    AdminEmployeesComponent,
    AdminAddClientComponent,
    AdminAddPricesComponent,
    AdminRoomsComponent,
    AdminAddRoomComponent,
    EmployeeProfileComponent,
    EmployeeAllClientsComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpModule,
    FormsModule,
    CustomFormsModule,
    RouterModule.forRoot(appRoutes),
    
  ],
  providers: [
    {provide: APP_BASE_HREF, useValue : '/' },
    UserService,
    ReservationService,
    RoomService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
