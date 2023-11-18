import {
  Component,
  ViewChild,
  ElementRef,
  Output,
  EventEmitter,
  Input,
} from '@angular/core';
import { fromEvent, Observable, of } from 'rxjs';
import {
  debounceTime,
  map,
  distinctUntilChanged,
  switchMap,
  tap,
} from 'rxjs/operators';
import { Account } from 'src/app/model/Account';

@Component({
  selector: 'app-autocompleteinput',
  templateUrl: './autocompleteinput.component.html',
  styleUrls: ['./autocompleteinput.component.scss'],
})
export class AutocompleteinputComponent {
  @ViewChild('searchInput') private searchInput!: ElementRef;
  @Output() setObjectEvent = new EventEmitter<{ name: string }>();
  @Input() placeholder = '';
  @Input() fetchData!: (searchWord: string) => Observable<any[]>;
  @Input() formatData!: (object: any) => string;
  showSearches = false;
  searchedObject: any[] = [];

  filter(name: string): Account[] {
    this.fetchData(name).subscribe((data) => {
      this.searchedObject = data;
      return data;
    });
    return [];
  }

  search() {
    const search$ = fromEvent(this.searchInput?.nativeElement, 'keyup').pipe(
      map((event: any) => event.target.value),
      debounceTime(500),
      distinctUntilChanged(),
      switchMap((term) => this.fetchData(term)),
      tap(() => {
        this.showSearches = true;
      }),
    );

    search$.subscribe((data) => {
      this.searchedObject = data;
    });
  }
  trackById(index: number, item: any): void {
    return item._id;
  }
  setObject(name: string) {
    this.searchedObject = this.filter(name);
    this.setObjectEvent.emit({ name });
    this.searchInput.nativeElement.value = name;
    this.showSearches = false;
  }
}
