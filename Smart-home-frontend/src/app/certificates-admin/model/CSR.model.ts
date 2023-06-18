export class CSR {
  constructor(
    public id: string,
    public email: string,
    public commonName: string,
    public organization: string,
    public organizationalUnit: string,
    public city: string,
    public state: string,
    public country: string,
    public keySize: string,
    public algorithm: string,
    public status: string
  ) {}
}
