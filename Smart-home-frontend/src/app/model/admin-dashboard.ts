export class AdminDashboard {
  constructor(
    public usersCount: number,
    public ownersCount: number,
    public tenantsCount: number,
    public propertiesCount: number,
    public devicesCount: number,
    public logsCount: number,
    public messagesCount: number,
    public alarmsCount: number,
    public logsCountInLastYearByMonths: { [key: string]: number },
    public messagesCountInLastYearByMonths: { [key: string]: number },
    public alarmsCountInLastYearByMonths: { [key: string]: number }
  ) {}
}
