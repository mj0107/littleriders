import Button from '@components/Shared/Button'
import Spacing from '@components/Shared/Spacing'

import { Shuttle } from '@types'
import { useNavigate } from 'react-router'

interface Props {
  data: Shuttle
}
export default function ShuttleCard({ data }: Props) {
  const navigate = useNavigate()
  const handleShuttleCardClick = () => {
    navigate(`/manage/drive-history/${data.shuttleId}`)
  }
  // const addDefaultImage = (e: SyntheticEvent<HTMLImageElement, Event>) => {
  //   e.currentTarget.src = data.imagePath
  // }
  return (
    <div className="w-[230px] flex-col items-center justify-center">
      <div className="relative ">
        <img
          className=" h-[150px] w-full rounded-md"
          src={`/api/content/${data.imagePath}`}
          alt="이미지"
          // onError={addDefaultImage}
        ></img>
        <strong className="text-xm absolute left-2 top-1 text-white">
          {data.name}
        </strong>
      </div>
      <Spacing style="h-1" />
      {data.terminalNumber === null ? (
        <Button
          color="bg-white border-[1px] border-lightgreen"
          full
          onClick={() => {}}
        >
          <span className="text-xs font-bold text-lightgreen">단말기 등록</span>
        </Button>
      ) : (
        <span className=" text-xs text-white">S10J12Ap312</span>
      )}
      <div className="flex items-center justify-between pt-2">
        <div className="flex flex-col">
          <strong className="text-xm">{data.type}</strong>
          <span className="text-xm text-darkgray">{data.licenseNumber}</span>
        </div>
        <Button onClick={handleShuttleCardClick}>
          <span className=" text-xs text-white">운행기록</span>
        </Button>
      </div>
    </div>
  )
}
