import DriverList from '@components/Academy/DriverList'
import EmployeeList from '@components/Academy/EmployeeList'
import Spacing from '@components/Shared/Spacing'
import ShuttleList from '@components/Shuttle/ShuttleList'

import Page from '@layouts/Page'

export default function OperatePage() {
  return (
    <Page>
      <Spacing style="h-[60px]" />
      <ShuttleList show={5} />
      <Spacing style="h-5" />
      <EmployeeList show={7} />
      <Spacing style="h-5" />
      <DriverList show={7} />a
      <Spacing style="h-5" />

    </Page>
  )
}
