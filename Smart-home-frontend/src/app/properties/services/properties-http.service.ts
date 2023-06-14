import { Inject, Injectable } from '@angular/core';
import { APP_SERVICE_CONFIG, AppConfig } from '../../app-config/app-config';
import { HttpClient, HttpParams } from "@angular/common/http";
import { Property } from '../../shared/model/property';
import {DeviceType} from "../../shared/model/device-type";
import { LogResponse } from "../model/log-response";
import { SortDirection } from "../../shared/model/sort-direction";
import { PageResponse } from "../../shared/model/page-response";
import { User } from "../../shared/model/user.model";

@Injectable({
  providedIn: 'root',
})
export class PropertiesHttpService {
  GET_USER_PROPERTIES = 'property/accessible/';
  GET_SELF_PROPERTIES = 'property/accessible';
  CREATE_PROPERTY = 'property';
  DELETE_PROPERTY = 'property/';
  ADD_PROPERTY_MEMBER = 'property/add-member';
  REMOVE_PROPERTY_MEMBER = 'property/remove-member';
  ADD_PROPERTY_DEVICE = 'property/add-device';
  GET_LOGS = 'log/search/'

  constructor(
    @Inject(APP_SERVICE_CONFIG) private config: AppConfig,
    private http: HttpClient
  ) {}

  getUserProperties(id: string) {
    return this.http.get<Property[]>(
      this.config.apiEndpoint + this.GET_USER_PROPERTIES + id
    );
  }

  getSelfProperties() {
    return this.http.get<Property[]>(
      this.config.apiEndpoint + this.GET_SELF_PROPERTIES
    );
  }

  createProperty(name: string, address: string, ownerId: string) {
    return this.http.post<Property>(
      this.config.apiEndpoint + this.CREATE_PROPERTY,
      {
        name,
        address,
        ownerId,
      }
    );
  }

  deleteProperty(id: string) {
    return this.http.delete(
      this.config.apiEndpoint + this.DELETE_PROPERTY + id
    );
  }

  addPropertyMember(userId: string, propertyId: string) {
    return this.http.put<Property>(
      this.config.apiEndpoint + this.ADD_PROPERTY_MEMBER,
      {
        userId,
        propertyId,
      }
    );
  }

  removePropertyMember(userId: string, propertyId: string) {
    return this.http.put<Property>(
      this.config.apiEndpoint + this.REMOVE_PROPERTY_MEMBER,
      {
        userId,
        propertyId,
      }
    );
  }

  addPropertyDevice(propertyId: string, name: string, deviceType: DeviceType, readPeriod: number, messageRegex: string) {
    return this.http.put<Property>(
      this.config.apiEndpoint + this.ADD_PROPERTY_DEVICE,
      {
        name,
        propertyId,
        deviceType,
        readPeriod,
        messageRegex
      }
    );
  }
  getLogs(
    id: string,
    pageSize?: number,
    pageNumber?: number,
    search?: string,
  ) {
    let params = new HttpParams();
    if (pageSize) {
      params = params.append('pageSize', pageSize);
    }
    if (pageNumber) {
      params = params.append('pageNumber', pageNumber);
    }
    if (search) {
      params = params.append('search', search);
    }
    return this.http.get<PageResponse<LogResponse>>(
      this.config.apiEndpoint + this.GET_LOGS + id,
      {
        params,
      }
    );
  }

}
