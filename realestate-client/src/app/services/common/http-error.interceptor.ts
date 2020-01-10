import { ToastrService } from 'ngx-toastr';
import {
    HttpEvent,
    HttpInterceptor,
    HttpHandler,
    HttpRequest,
    HttpErrorResponse
   } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { Injectable } from '@angular/core';

@Injectable()
export class HttpErrorInterceptor implements HttpInterceptor {
    constructor(private toastr: ToastrService) {

    }
    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
      return next.handle(request)
        .pipe(
          retry(1),
          catchError((error: HttpErrorResponse) => {
            let errorMessage = '';
            if (error.error instanceof ErrorEvent) {
              // client-side error
              errorMessage = `Lỗi: ${error.error.message}`;
            } else {
              // server-side error
              errorMessage = `Lỗi server mã: ${error.status} \r\nNội dung: ${error.message}`;
            }
            // this.toastr.error(errorMessage);
            return throwError(errorMessage);
          })
        );
    } 
   }
