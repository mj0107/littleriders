import { useEffect, useState } from 'react'

import { UniqueIdentifier } from '@dnd-kit/core'
import { useSortable } from '@dnd-kit/sortable'
import { CSS } from '@dnd-kit/utilities'
import { ChildInfo } from '@types'
import { FaChild, FaTrashAlt } from 'react-icons/fa'
import { MdDragHandle } from 'react-icons/md'

interface Props {
  id: UniqueIdentifier
  selectedStation?: number
  name: string
  type?: string
  index: number
  childList?: ChildInfo[]
  onClick?: (id: number) => void
  onHover?: () => void
  handleStationRemoveClick?: (
    e: React.MouseEvent<SVGElement, MouseEvent>,
    id: UniqueIdentifier,
  ) => void
}

export function SortableItem({
  id,
  selectedStation,
  name,
  type,
  index,
  childList,
  onClick,
  handleStationRemoveClick,
}: Props) {
  const [isClicked, setIsClicked] = useState<boolean>(false)
  const [isMouseOver, setIsMouseOver] = useState<boolean>(false)
  const [childCount, setChildCount] = useState<number | undefined>(
    childList?.length,
  )
  const { attributes, listeners, setNodeRef, transform, transition } =
    useSortable({ id })

  const sortIcon = (type: string | undefined) => {
    if (!type) {
      return (
        <div className="flex w-8 justify-center">
          <MdDragHandle size={30} />
        </div>
      )
    }
    if (type === 'selectedStationList')
      return <p className="w-8 text-center text-xl font-bold">{index}</p>
    if (type === 'stationList')
      return <img src="/src/assets/image/bus-stop-icon.svg" className="w-8" />
    if (type.includes('childList')) return <></>
  }

  const stationTailIcon = (type: string | undefined) => {
    if (type === 'selectedStationList')
      return (
        <div className="flex items-center justify-center text-gray-500">
          <FaChild />
          <p className="w-8 text-center text-xl font-bold">{childCount}</p>
        </div>
      )
    else if (type === 'stationList') {
      return (
        <FaTrashAlt
          className="hover:scale-125 hover:text-red active:scale-150 active:text-red"
          onClick={(e) => {
            if (handleStationRemoveClick) handleStationRemoveClick(e, id)
          }}
        />
      )
    }
    return <></>
  }

  useEffect(() => {
    setChildCount(childList?.length)
  }, [childList])

  useEffect(() => {
    if (id) {
      setIsClicked(selectedStation === Number(id.toString()))
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [selectedStation])
  return (
    <div
      style={{ transform: CSS.Transform.toString(transform), transition }}
      ref={setNodeRef}
      {...attributes}
      {...listeners}
      className={`m-2 h-auto w-60 rounded-sm border-2 p-3 shadow-sm transition-colors ${isClicked ? ' border-lightgreen bg-lightgreen text-white' : isMouseOver ? 'border-lightgreen bg-white' : 'bg-white'} `}
      onClick={() => {
        if (onClick) {
          onClick(Number(id.toString()))
        }
      }}
      onMouseOver={() => {
        setIsMouseOver(true)
      }}
      onMouseOut={() => {
        setIsMouseOver(false)
      }}
    >
      <div className="flex items-center justify-start">
        {sortIcon(type)}
        <div className="flex w-full items-center justify-between">
          <p className="ms-2 text-center">{name}</p>
          {stationTailIcon(type)}
        </div>
      </div>
    </div>
  )
}
