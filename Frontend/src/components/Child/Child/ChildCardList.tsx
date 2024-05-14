import ChildCard from '@components/Child/Child/ChildCard'

import { ChildInfo } from '@types'

interface Props {
  academyChildList: ChildInfo[]
}

export default function ChildCardList({ academyChildList }: Props) {
  return (
    <div className="grid grid-cols-6 gap-4">
      {academyChildList.map((child: ChildInfo) => (
        <ChildCard key={child.academyChildId} childInfo={child} />
      ))}
    </div>
  )
}
