import { Capabilities } from './capabilities.model';

export class CertificateType {
  constructor(
    public id: string,
    public name: string,
    public capabilities: Capabilities[]
  ) {}
}
